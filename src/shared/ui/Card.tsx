import {ReactNode} from 'react';

import {cn} from '@shared/helpers';

type Props = {
	children: ReactNode;
	className?: string;
	withoutTopRounded?: boolean;
	withoutBottomRounded?: boolean;
};

// TODO:
//  я карточка, рендерю какой-то тайтл слева, какую-то иконку справа, какие-то кнопки снизу и какие-то list-items
//  1. add prop featureConfig -> map -> <button />

export function Card(props: Props) {
	const {children, className, withoutTopRounded, withoutBottomRounded} = props;

	return (
		<div
			role='card-wrapper'
			className={cn(
				'rounded-2xl bg-white',
				withoutTopRounded && 'rounded-tl-none rounded-tr-none',
				withoutBottomRounded && 'rounded-bl-none rounded-br-none',
				className,
			)}
		>
			{children}
		</div>
	);
}
